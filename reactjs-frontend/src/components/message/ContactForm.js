import React, { useRef } from 'react';
import { Button, Form, Input, notification } from 'antd';
import { sendMessage } from './messageService';

function ContactForm() {
    const formRef = useRef();

    const handleFinish = async (values) => {
        await sendMessage(values)
            .then(() => {
                formRef.current.resetFields(); // Reset the form after form submission
                notification.success({
					message: 'Thank You!',
					description: 'We successfully received your message',
					placement: 'bottomLeft'
				});
            })
            .catch(error => {
                // Handle send message error
                notification.error({
                    message: 'Error',
                    description: error.message || 'Something went wrong.',
                    placement: 'bottomLeft'
                });
            });
    }

    const preventInputSpacing = (e) => {
        if (e.key === ' ') {
            e.preventDefault();
        }
    };

    return (
        <Form 
            ref={formRef}
            onFinish={handleFinish}
        >
            <Form.Item
                name="name"
                rules={[
                    {
                        required: true,
                        message: 'Please input your name',
                    },
                ]}
                style={{ textAlign: 'left' }}
            >
                <Input
                    placeholder="Name"
                />
            </Form.Item>
            <Form.Item
                name="email"
                rules={[
                    {
                        required: true,
                        message: 'Please input your email',
                    },
                    {
                        type: 'email',
                        message: 'Invalid email',
                    }
                ]}
            >
                <Input
                    placeholder="Email"
                    onKeyDown={preventInputSpacing}
                />
            </Form.Item>

            <Form.Item
                name="text"
                rules={[
                    {
                        required: true,
                        message: 'Please input your message',
                    },
                ]}
            >
                <Input.TextArea
                    rows={4}
                    placeholder="Anything to tell us?"
                />
            </Form.Item>

            <Form.Item shouldUpdate>
                {() => (
                    <Button
                        type="primary"
                        htmlType="submit"
                        style={{ width: '100%' }}
                    >
                        Send
                    </Button>
                )}
            </Form.Item>
        </Form>
    );
}

export default ContactForm;
