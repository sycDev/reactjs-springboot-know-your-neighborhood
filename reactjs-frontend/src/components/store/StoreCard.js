import React from 'react';
import { Card, Modal } from 'antd';
import {
    PhoneOutlined,
    ClockCircleOutlined,
    EnvironmentOutlined,
    EditOutlined,
    DeleteOutlined
} from '@ant-design/icons';
import './StoreCard.css';

function StoreCard({ store, onEdit, onDelete }) {
    const handleEdit = () => {
        onEdit(store);
    };

    const handleDelete = () => {
        // Show the delete confirmation modal
        Modal.confirm({
            title: 'Confirm Delete',
            content: 'Are you sure you want to delete this store?',
            okText: 'Yes',
            cancelText: 'No',
            onOk: () => {
                onDelete(store.storeId);
            }
        });
    };

    return (
        <>
            <Card
                title={`${store.storeName}`}
                className="store-card"
                actions={[
                    <EditOutlined key="edit" onClick={handleEdit} />,
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
