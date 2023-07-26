import React from 'react';
import { Typography } from 'antd';
import TabTitle from '../common/TabTitle';
import './Terms.css';
const { Title, Paragraph, Text } = Typography;

function Terms() {
    return (
        <>
            <TabTitle title="KYN | Terms and Conditions" />
            <Typography className="terms">
                <Title level={2} className="title">Terms and Conditions</Title>
                <Text className="small">Last Updated July 25th, 2023</Text>

                <Paragraph className="paragraph">
                    Welcome to Know-Your-Neighborhood! These Terms and Conditions outline the agreement between you
                    (referred to as "user" or "you") and Know-Your-Neighborhood (referred to as "we," "us," or "our")
                    regarding your use of our website and services. By accessing or using Know-Your-Neighborhood, you
                    agree to comply with these Terms and Conditions. Please read them carefully.
                </Paragraph>

                <Title level={4}>Use of the Website</Title>
                <Paragraph className="paragraph">
                    The Website's primary purpose is to provide information about new grocery store locations 
                    opened by the Company in various neighborhoods. You must be at least 18 years old to use 
                    this Website. You agree to use the Website only for lawful purposes and in a manner that 
                    does not violate any applicable laws or regulations.
                </Paragraph>

                <Title level={4}>Website Content</Title>
                <Paragraph className="paragraph">
                    The Company strives to provide accurate and up-to-date information on store locations and 
                    other relevant details. However, the Company does not guarantee the accuracy, completeness, 
                    or reliability of the information provided on the Website. The Website's content is subject 
                    to change without prior notice. The Company reserves the right to update, modify, or remove 
                    content at any time.
                </Paragraph>

                <Title level={4}>User Account</Title>
                <Paragraph className="paragraph">
                    To access certain features of Know-Your-Neighborhood, you may need to create a user account.
                    You are responsible for maintaining the confidentiality of your account credentials
                    and ensuring that the information you provide is accurate and up-to-date. You agree
                    to be solely responsible for all activities that occur under your account.
                </Paragraph>

                <Title level={4}>Intellectual Property</Title>
                <Paragraph className="paragraph">
                    All content, logos, trademarks, and other intellectual property on the Website are 
                    owned by the Company or its licensors. You are not granted any rights or licenses to 
                    use any of the intellectual property without the Company's express written permission.
                </Paragraph>

                <Title level={4}>Disclaimer of Warranties</Title>
                <Paragraph className="paragraph">
                    Know-Your-Neighborhood is provided on an "as is" and "as available" basis, without
                    any warranties or representations, expressed or implied. We do not warrant
                    that Know-Your-Neighborhood will be uninterrupted, error-free, or secure. Your use of
                    Know-Your-Neighborhood is at your own risk.
                </Paragraph>

                <Title level={4}>Limitation of Liability</Title>
                <Paragraph className="paragraph">
                    In no event shall Know-Your-Neighborhood or its affiliates be liable for any indirect,
                    incidental, special, or consequential damages arising out of or in connection
                    with your use of Know-Your-Neighborhood. Our liability to you shall not exceed
                    the amount you paid, if any, for accessing or using Know-Your-Neighborhood.
                </Paragraph>

                <Title level={4}>Third-Party Content and Links</Title>
                <Paragraph className="paragraph">
                    Know-Your-Neighborhood may contain links to third-party websites, advertisements,
                    or content. We are not responsible for the availability, accuracy,
                    or legality of any third-party content. Your interactions with
                    third-party websites or services are solely between you and the third party.
                </Paragraph>

                <Title level={4}>Modifications to the Terms</Title>
                <Paragraph className="paragraph">
                    The Company reserves the right to modify or update these Terms at any time without prior notice. 
                    Any changes will be effective immediately upon posting on the Website. Your continued use of the 
                    Website after changes are made constitutes your acceptance of the revised Terms.
                </Paragraph>

                <Title level={4}>Governing Law</Title>
                <Paragraph className="paragraph">
                    These Terms shall be governed by and construed in accordance with the laws of Malaysia,
                    and any disputes arising from or in connection with these Terms shall be subject to the exclusive 
                    jurisdiction of the courts in Malaysia.
                </Paragraph>

                <Title level={4}>Contact Us</Title>
                <Paragraph className="paragraph">
                    If you have any questions or concerns regarding these Terms and Conditions, 
                    please contact us at <a href="mailto:info@kyn.com"> info@kyn.com</a>
                </Paragraph>

                <Paragraph className="paragraph">
                    By using Know-Your-Neighborhood, you acknowledge that you have read, understood, and agreed to 
                    these Terms and Conditions. Thank you for choosing Know-Your-Neighborhood, and we wish you a seamless 
                    and satisfying experience.
                </Paragraph>
            </Typography>
        </>
    );
}

export default Terms;
