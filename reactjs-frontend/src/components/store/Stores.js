import React, { useEffect, useState } from 'react';
import { Button, Col, Empty, Row, Skeleton, Space, notification } from 'antd';
import { SearchOutlined } from '@ant-design/icons';
import Title from 'antd/es/typography/Title';
import TabTitle from '../common/TabTitle';
import StoreCard from './StoreCard';
import StoreForm from './StoreForm';
import { createStore, deleteSingleStore, getAllStores, updateStore } from './storeService';
import NoDataImg from '../../assets/images/no-data.svg';
import './Stores.css';

function Stores() {
    const [isLoading, setIsLoading] = useState(false);
    const [stores, setStores] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [editStoreData, setEditStoreData] = useState(null);

    const handleAddStore = () => {
        setShowForm(true);
        setEditStoreData(null);
    };

    const handleEditStore = (store) => {
        setShowForm(true);
        setEditStoreData(store);
    };

    const handleCloseModal = () => {
        setShowForm(false);
    };

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

    const handleFormSubmit = async (formData) => {
        if (editStoreData) {
            // Editing an existing store
            await updateStore(editStoreData.storeId, formData)
                .then(() => {
                    fetchAllStores();
                    notification.success({
                        message: 'Success',
                        description: 'Store successfully updated',
                        placement: 'bottomLeft'
                    });
                })
                .catch(error => {
                    notification.error({
                        message: 'Error',
                        description: error.message || 'Something went wrong.',
                        placement: 'bottomLeft'
                    });
                });
        } else {
            // Adding a new store
            await createStore(formData)
                .then(() => {
                    fetchAllStores();
                    notification.success({
                        message: 'Success',
                        description: 'Store successfully added',
                        placement: 'bottomLeft'
                    });
                })
                .catch(error => {
                    notification.error({
                        message: 'Error',
                        description: error.message || 'Something went wrong.',
                        placement: 'bottomLeft'
                    });
                });
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

    // For skeleton while loading the store list
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
        // Loading
        content = <Row gutter={[16, 16]}>{skeletonCards}</Row>;
    } else if (stores.length > 0) {
        // Store records found
        content = (
            <Row gutter={[16, 16]}>
                {stores.map((store) => (
                    <Col key={store.storeId} xs={24} sm={12} md={8} lg={6}>
                        <StoreCard store={store} onEdit={handleEditStore} onDelete={handleDelete} />
                    </Col>
                ))}
            </Row>
        );
    } else {
        // Empty store record
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
                    <Button type="primary" onClick={handleAddStore}>
                        Add Store
                    </Button>
                </Space>
                {content}
            </div>

            {showForm && (
                // For showing the add and edit modal form 
                <StoreForm
                    editStoreData={editStoreData}
                    onCancel={handleCloseModal}
                    onSubmit={handleFormSubmit}
                />
            )}
        </>
    );
}

export default Stores;
