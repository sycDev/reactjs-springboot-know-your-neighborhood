import React from 'react';
import { Col, Row } from 'antd';
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
import ContactForm from '../message/ContactForm';
import './Contact.css';

function Contact() {
    return (
        <>
            <TabTitle title="KYN | Contact Us" />
            <section className="contact">
                <Title level={2} className="section-title">Contact Us</Title>
                <Row>
                    <Col xs={24} lg={12} style={{ padding: '0 50px' }}>
                        <ContactForm />
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
