import React, { useEffect } from 'react';
import { Button, Divider, Form, Input, notification } from 'antd';
import Title from 'antd/es/typography/Title';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import TabTitle from '../common/TabTitle';
import SocialLogin from './SocialLogin';
import { GOOGLE_AUTH_URL } from "../utils/constant";
import GoogleImg from '../../assets/images/google.png';
import { login } from './authService';
import './Login.css';

function Login({ onLogin }) {
    const handleFinish = async (values) => {
        const request = {
            emailOrUsername: values.username,
            password: values.password
        };

        try {
            // Process login
            await login(request);
            onLogin();
        } catch (error) {
            // Handle login error
            if (error.response.status === 401) {
                notification.error({
                    message: 'Invalid Credentials',
                    description: 'Please try again!',
                    placement: 'bottomLeft'
                });
            } else {
                notification.error({
                    message: 'Error',
                    description: error.message || 'Something went wrong.',
                    placement: 'bottomLeft'
                });
            }
        }
    }

    useEffect(() => {
        // Function to extract query parameters from the URL
        const getUrlParameter = (name) => {
            const urlSearchParams = new URLSearchParams(window.location.search);
            return urlSearchParams.get(name);
        };

        // Show alert error message if error parameter is present
        const error = getUrlParameter('error');
        if (error) {
            setTimeout(() => {
                notification.error({
                    message: 'Oops',
                    description: error,
                    placement: 'bottomLeft'
                });
                // Remove the error query parameter from the url
                window.history.replaceState(null, '', '/login');
            }, 100);
        }
    })

    return (
        <div style={{ textAlign: 'center', width: '100%' }}>
            <TabTitle title="KYN | Login" />
            <Form
                className="login-form"
                onFinish={handleFinish}
            >
                <Title level={2}>Welcome Back!</Title>
                <SocialLogin 
                    href={GOOGLE_AUTH_URL}
                    imgSrc={GoogleImg}
                    imgAlt="Google Login"
                />
                <Divider>
                    <small>OR</small>
                </Divider>
                <Form.Item
                    name="username"
                    rules={[
                        {
                            required: true,
                            message: 'Please input email or username',
                        },
                    ]}
                    style={{ textAlign: 'left' }}
                >
                    <Input
                        prefix={<UserOutlined className="site-form-item-icon" />}
                        placeholder=" Email or Username"
                    />
                </Form.Item>
                <Form.Item
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'Please input password',
                        },
                    ]}
                    style={{ textAlign: 'left' }}
                >
                    <Input
                        prefix={<LockOutlined className="site-form-item-icon" />}
                        type="password"
                        placeholder=" Password"
                    />
                </Form.Item>
                <Form.Item shouldUpdate>
                    {() => (
                        <Button
                            type="primary"
                            htmlType="submit"
                            style={{ width: '100%' }}
                        >
                            Login
                        </Button>
                    )}
                </Form.Item>
            </Form>
        </div>
    );
}

export default Login;
