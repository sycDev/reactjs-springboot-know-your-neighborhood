import React from 'react';
import { Link } from 'react-router-dom';
import { Card, Modal } from 'antd';
import {
    PhoneOutlined,
    ClockCircleOutlined,
    EnvironmentOutlined,
    EditOutlined,
    DeleteOutlined
} from '@ant-design/icons';
import './StoreCard.css';

function StoreCard(props) {
    const { store } = props;

    const handleDelete = async () => {
        // Show the confirmation modal
        Modal.confirm({
            title: 'Confirm Delete',
            content: 'Are you sure you want to delete this store?',
            okText: 'Yes',
            cancelText: 'No',
            onOk: () => {
                props.onDelete(store.storeId);
            }
        });
    };

    return (
        <>
            <Card
                title={`${store.storeName}`}
                className="store-card"
                actions={[
                    <Link to={`/stores/edit/${store.storeId}`} key="edit-link">
                        <EditOutlined key="edit" />
                    </Link>,
                    <DeleteOutlined key="delete" onClick={handleDelete} />
                ]}
            >
                <div className="card-content">
                    <p><PhoneOutlined /> {store.contactNumber}</p>
                    <p><ClockCircleOutlined /> {store.openingHours}</p>
                    <p><EnvironmentOutlined /> {store.address}</p>
                </div>
            </Card>
        </>
    );
}

export default StoreCard;
