import React from 'react';
import { Button, Col, Form, Input, Row } from 'antd';
import Title from 'antd/es/typography/Title';
import { 
    FacebookOutlined, 
    InstagramOutlined, 
    TwitterOutlined, 
    LinkedinOutlined, 
    EnvironmentOutlined, 
    PhoneOutlined, 
    MailOutlined 
} from '@ant-design/icons';
import TabTitle from '../common/TabTitle';
import './Contact.css';

function Contact() {
    const handleFinish = (values) => {
        console.log(values);
    }

    const preventInputSpacing = (e) => {
        if (e.key === ' ') {
            e.preventDefault();
        }
    };

    return (
        <>
            <TabTitle title="KYN | Contact Us" />
            <section className="contact">
                <Title level={2} className="section-title">Contact Us</Title>
                <Row>
                    <Col xs={24} lg={12} style={{ padding: '0 50px' }}>
                        <Form
                            className="contact-form"
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
                    </Col>
                    <Col xs={24} lg={12} style={{ padding: '0 50px' }}>
                        <div>
                            <ul className="contact-list">
                                <li className="list-item">
                                    <EnvironmentOutlined style={{ fontSize: '24px', color: '#08c' }} />
                                    <span className="contact-text">
                                        Penang, Malaysia
                                    </span>
                                </li>

                                <li className="list-item">
                                    <PhoneOutlined style={{ fontSize: '24px', color: '#08c' }} />
                                    <span className="contact-text">
                                        <a href="tel:1-212-555-5555" title="Call us">
                                            03 - 656 7893
                                        </a>
                                    </span>
                                </li>

                                <li className="list-item">
                                    <MailOutlined style={{ fontSize: '24px', color: '#08c' }}/>
                                    <span className="contact-text">
                                        <a href="mailto:#" title="Send email">
                                            info@kyn.com
                                        </a>
                                    </span>
                                </li>

                            </ul>

                            <hr/>
                            <ul className="social-media-list">
                                <li>
                                    <a href="#facebook" target="_blank" className="contact-icon">
                                        <FacebookOutlined />
                                    </a>
                                </li>
                                <li>
                                    <a href="#instagram" target="_blank" className="contact-icon">
                                        <InstagramOutlined />
                                    </a>
                                </li>
                                <li><a href="#twitter" target="_blank" className="contact-icon">
                                    <TwitterOutlined />
                                </a>
                                </li>
                                <li>
                                    <a href="#linkedin" target="_blank" className="contact-icon">
                                        <LinkedinOutlined />
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </Col>
                </Row>
            </section>
        </>
    );
}

export default Contact;
