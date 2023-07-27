import React, { useEffect, useState } from 'react';
import { Form, Input, Button, Modal } from 'antd';
import {
    STORE_ADDRESS_MAX_LENGTH,
    STORE_CONTACT_MAX_LENGTH,
    STORE_HOURS_MAX_LENGTH,
    STORE_NAME_MAX_LENGTH,
    STORE_NAME_MIN_LENGTH
} from '../utils/constant';

function StoreForm({ editStoreData, onCancel, onSubmit }) {
    const [form] = Form.useForm();
    const [isOpen] = useState(true);

    useEffect(() => {
        if (editStoreData) {
            // Populate form fields when in edit mode
            form.setFieldsValue(editStoreData);
        }
    }, [editStoreData, form]);

    const handleSubmit = async () => {
        try {
            const formData = await form.validateFields();
            onSubmit(formData);
            onCancel();
        } catch (error) {
            console.error('Form validation failed: ', error);
        }
    };

    return (
        <>
            <Modal
                title={editStoreData ? 'Edit Store' : 'Add New Store'}
                open={isOpen}
                onCancel={onCancel}
                maskClosable={false}
                footer={[
                    <Button key="submit" type="primary" onClick={handleSubmit}>
                        {editStoreData ? 'Save Changes' : 'Submit'}
                    </Button>
                ]}
            >
                <Form
                    className="store-form"
                    layout="vertical"
                    form={form}
                >
                    <Form.Item
                        name="storeName"
                        label="Store Name"
                        rules={[
                            {
                                required: true,
                                message: 'Please input the store name',
                            },
                            {
                                min: STORE_NAME_MIN_LENGTH,
                                message: `Minimum ${STORE_NAME_MIN_LENGTH} characters`
                            },
                            {
                                max: STORE_NAME_MAX_LENGTH,
                                message: `Cannot exceed ${STORE_NAME_MAX_LENGTH} characters`
                            }
                        ]}
                    >
                        <Input />
                    </Form.Item>
                    <Form.Item
                        name="contactNumber"
                        label="Contact Number"
                        rules={[
                            {
                                required: true,
                                message: 'Please input the contact number',
                            },
                            {
                                max: STORE_CONTACT_MAX_LENGTH,
                                message: `Cannot exceed ${STORE_CONTACT_MAX_LENGTH} characters`
                            }
                        ]}
                    >
                        <Input />
                    </Form.Item>
                    <Form.Item
                        name="openingHours"
                        label="Opening Hours"
                        rules={[
                            {
                                required: true,
                                message: 'Please input the opening hours',
                            },
                            {
                                max: STORE_HOURS_MAX_LENGTH,
                                message: `Cannot exceed ${STORE_HOURS_MAX_LENGTH} characters`
                            }
                        ]}
                    >
                        <Input.TextArea showCount maxLength={STORE_HOURS_MAX_LENGTH} autoSize />
                    </Form.Item>
                    <Form.Item
                        name="address"
                        label="Address"
                        rules={[
                            {
                                required: true,
                                message: 'Please input the address',
                            },
                            {
                                max: STORE_ADDRESS_MAX_LENGTH,
                                message: `Cannot exceed ${STORE_ADDRESS_MAX_LENGTH} characters`
                            }
                        ]}
                    >
                        <Input.TextArea showCount maxLength={STORE_ADDRESS_MAX_LENGTH} autoSize />
                    </Form.Item>
                </Form>
            </Modal>
        </>
    );
}

export default StoreForm;
