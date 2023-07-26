import React, { useState } from 'react';
import { Button, Checkbox, Form, Input, Modal } from 'antd';
import { MailOutlined, LockOutlined, UserOutlined } from '@ant-design/icons';
import Title from 'antd/es/typography/Title';
import {
	EMAIL_MAX_LENGTH,
	PASSWORD_MAX_LENGTH,
	PASSWORD_MIN_LENGTH,
	USERNAME_MAX_LENGTH,
	USERNAME_MIN_LENGTH
} from '../utils/constant';
import TabTitle from '../common/TabTitle';
import Terms from '../pages/Terms';
import './Register.css';

function Register() {
    const [open, setOpen] = useState(false);

    const preventInputSpacing = (e) => {
        if (e.key === ' ') {
            e.preventDefault();
        }
    };

    const validateNoSpaces = (_, value) => {
        if (value && value.indexOf(' ') !== -1) {
            return Promise.reject(new Error('No spaces allowed'));
        }
        return Promise.resolve();
    };

    const showTermsModal = () => {
        setOpen(true);
    };

    const closeTermsModal = () => {
        setOpen(false);
    };

    const handleFinish = (values) => {
        console.log(values);
    };

    return (
        <>
            <TabTitle title="KYN | Register" />
            <Form
                className="register-form"
                onFinish={handleFinish}
            >
                <Title level={2} style={{ textAlign: 'center' }}>Registration</Title>
                <Form.Item
                    name="username"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your username',
                        },
                        {
                            min: USERNAME_MIN_LENGTH,
                            message: `Minimum ${USERNAME_MIN_LENGTH} characters`
                        },
                        {
                            max: USERNAME_MAX_LENGTH,
                            message: `Cannot exceed ${USERNAME_MAX_LENGTH} characters`
                        }
                    ]}
                    hasFeedback
                >
                    <Input
                        prefix={<UserOutlined className="site-form-item-icon" />}
                        placeholder=" Username *"
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
                        },
                        {
                            max: EMAIL_MAX_LENGTH,
                            message: `Cannot exceed ${EMAIL_MAX_LENGTH} characters`
                        }
                    ]}
                    hasFeedback
                >
                    <Input
                        prefix={<MailOutlined className="site-form-item-icon" />}
                        placeholder=" Email *"
                        onKeyDown={preventInputSpacing}
                    />
                </Form.Item>

                <Form.Item
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your password',
                        },
                        {
                            pattern: /(?=.*[A-Z])/,
                            message: 'At least 1 uppercase letter'
                        },
                        {
                            pattern: /(?=.*?[a-z])/,
                            message: 'At least 1 lowercase letter'
                        },
                        {
                            pattern: /(?=.*?\d)/,
                            message: 'At least 1 digit'
                        },
                        {
                            pattern: /(?=.*?[#?!@$%^&-/.])/,
                            message: 'At least 1 special character'
                        },
                        {
                            min: PASSWORD_MIN_LENGTH,
                            message: `Minimum ${PASSWORD_MIN_LENGTH} characters`
                        },
                        {
                            max: PASSWORD_MAX_LENGTH,
                            message: `Cannot exceed ${PASSWORD_MAX_LENGTH} characters`
                        },
                        { validator: validateNoSpaces }
                    ]}
                    hasFeedback
                >
                    <Input.Password
                        prefix={<LockOutlined className="site-form-item-icon" />}
                        placeholder=" Password *"
                        onKeyDown={preventInputSpacing}
                    />
                </Form.Item>

                <Form.Item
                    name="confirm"
                    dependencies={['password']}
                    hasFeedback
                    rules={[
                        {
                            required: true,
                            message: 'Please confirm your password',
                        },
                        ({ getFieldValue }) => ({
                            validator(_, value) {
                                if (!value || getFieldValue('password') === value) {
                                    return Promise.resolve();
                                }
                                return Promise.reject(new Error('Passwords does not match'));
                            },
                        }),
                        { validator: validateNoSpaces }
                    ]}
                >
                    <Input.Password
                        prefix={<LockOutlined className="site-form-item-icon" />}
                        placeholder=" Confirm Password *"
                        onKeyDown={preventInputSpacing}
                    />
                </Form.Item>

                <Form.Item
                    name="agreement"
                    valuePropName="checked"
                    rules={[
                        {
                            validator: (_, value) =>
                                value ? Promise.resolve() : Promise.reject(
                                    new Error('Should read and accept the terms')
                                ),
                        },
                    ]}
                >
                    <Checkbox>
                        I have read and agree to the 
                        <a href="#terms" onClick={showTermsModal}> terms</a>
                    </Checkbox>
                </Form.Item>

                <Modal
                    width={1000}
                    open={open}
                    onOk={closeTermsModal}
                    onCancel={closeTermsModal}
                >
                    <Terms />
                </Modal>

                <Form.Item>
                    <Button 
                        type="primary" 
                        htmlType="submit"
                        style={{ width: '100%' }}
                    >
                        Register
                    </Button>
                </Form.Item>
            </Form>
        </>
    );
}

export default Register;
