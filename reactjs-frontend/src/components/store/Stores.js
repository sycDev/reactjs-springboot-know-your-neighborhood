import React, { useEffect, useState } from 'react';
import { Button, Col, Empty, Row, Skeleton, Space, notification } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import Title from 'antd/es/typography/Title';
import TabTitle from '../common/TabTitle';
import StoreCard from './StoreCard';
import { deleteSingleStore, getAllStores } from './storeService';
import NoDataImg from '../../assets/images/no-data.svg';
import './Stores.css';

function Stores() {
    const [isLoading, setIsLoading] = useState(false);
    const [stores, setStores] = useState([]);

    const fetchAllStores = async () => {
        try {
            setIsLoading(true);
            // Simulate a delay of 1 second before fetching data
            await new Promise((resolve) => setTimeout(resolve, 1000));
            const response = await getAllStores();
            setStores(response);
        } catch (error) {
            notification.error({
                message: 'Error',
                description: error.message || 'Something went wrong.',
                placement: 'bottomLeft',
            });
        } finally {
            setIsLoading(false);
        }
    };

    const handleDelete = async (storeId) => {
        await deleteSingleStore(storeId)
            .then(() => {
                fetchAllStores();
                notification.success({
                    message: 'Success',
                    description: 'Store successfully deleted',
                    placement: 'bottomLeft'
                });
            }).catch(error => {
                // Handle delete store error
                if (error.response.status === 403) {
                    notification.warning({
                        message: 'Access Denied',
                        description: 'Sorry, you are not allowed to delete store',
                        placement: 'bottomLeft'
                    });
                } else {
                    notification.error({
                        message: 'Error',
                        description: error.message || 'Something went wrong.',
                        placement: 'bottomLeft'
                    });
                }
            });
    };

    useEffect(() => {
        fetchAllStores();
    }, []);

    let cardIdCounter = 1;
    const skeletonCards = new Array(4).fill().map(() => {
        const cardId = cardIdCounter++;
        return (
            <Col key={cardId} xs={24} sm={12} md={8} lg={6}>
                <div className="cardSkeleton">
                    <div className="cardSkeletonImage"></div>
                    <div className="cardSkeletonContent">
                        <Skeleton active />
                    </div>
                </div>
            </Col>
        );
    });
    
    let content;
    if (isLoading) {
        content = <Row gutter={[16, 16]}>{skeletonCards}</Row>;
    } else if (stores.length > 0) {
        content = (
            <Row gutter={[16, 16]}>
                {stores.map((store) => (
                    <Col key={store.storeId} xs={24} sm={12} md={8} lg={6}>
                        <StoreCard store={store} onDelete={handleDelete} />
                    </Col>
                ))}
            </Row>
        );
    } else {
        content = (
            <Empty
                className="emptyResult"
                image={NoDataImg}
                imageStyle={{
                    height: 60,
                }}
                description={<span>No Stores Found</span>}
            />
        );
    }

    return (
        <>
            <TabTitle title="KYN | Stores" />
            <div className="stores-container">
                <Title level={2} className="page-title">Stores</Title>
                <Space align="baseline" className="action-btn">
                    <Button icon={<SearchOutlined />} href="/stores/search">
                        Search
                    </Button>
                    <Button type="primary" href="/stores/new">
                        Add Store
                    </Button>
                </Space>
                {content}
            </div>
        </>
    );
}

export default Stores;
