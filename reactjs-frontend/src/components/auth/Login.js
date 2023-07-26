import React from 'react';
import { Button, Divider, Form, Input } from 'antd';
import Title from 'antd/es/typography/Title';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import TabTitle from '../common/TabTitle';
import './Login.css';
import SocialLogin from './SocialLogin';
import { GOOGLE_AUTH_URL } from "../utils/constant";
import GoogleImg from '../../assets/images/google.png';

function Login() {
    const handleFinish = (values) => {
        console.log(values);
    }

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
