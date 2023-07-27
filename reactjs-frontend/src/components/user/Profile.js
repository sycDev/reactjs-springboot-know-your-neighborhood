import React, { useEffect, useState } from 'react';
import { Card, Avatar, Spin, notification } from 'antd';
import Title from 'antd/es/typography/Title';
import Paragraph from 'antd/es/typography/Paragraph';
import TabTitle from '../common/TabTitle';
import { getProfileById } from './userService';
import './Profile.css';
const { Meta } = Card;

function Profile(props) {
    const { currentUser } = props;
    const [profile, setProfile] = useState(null);

    useEffect(() => {
        const fetchProfileDetails = async () => {
            await getProfileById(currentUser.userId)
                .then((response) => {
                    setProfile(response);
                }).catch((error) => {
                    notification.error({
                        message: 'Error',
                        description: error.message || 'Something went wrong.',
                        placement: 'bottomLeft'
                    });
                });
        };

        fetchProfileDetails();
    }, [currentUser.userId]);

    return (
        <>
            <TabTitle title="KYN | My Profile" />
            <Title level={2} style={{ textAlign: 'center' }}>My Profile</Title>
            <div className="profile-container">
                {profile ? (
                    <Card className="card-container">
                        <div className="avatar-container">
                            {profile.imgUrl ? (
                                <Avatar
                                    size={100}
                                    src={<img
                                        src={profile.imgUrl}
                                        alt="User avatar"
                                        onError={(e) => {
                                            // Hide the image if it fails to load
                                            e.target.style.display = 'none';
                                        }}
                                        referrerPolicy="no-referrer"
                                    />}
                                />
                            ) : (
                                <Avatar
                                    size={100}
                                    style={{
                                        backgroundColor: '#fde3cf',
                                        color: '#f56a00',
                                        fontSize: '36px'
                                    }}
                                >
                                    {profile.name ? profile.name.charAt(0).toUpperCase() : profile.username.charAt(0).toUpperCase()}
                                </Avatar>
                            )}
                        </div>
                        <Meta title={profile.name} description={`@${profile.username}`} />
                        <Paragraph>{profile.email}</Paragraph>
                        <Paragraph>Joined {formatDate(profile.joinedAt)}</Paragraph>
                    </Card>
                ) : (
                    <Spin 
                        size="large" 
                        style={
                            {
                                fontSize: 30, 
                                display: 'block', 
                                textAlign: 'center', 
                                margin: '12% auto'
                            }
                        }
                    />
                )}
            </div>
        </>
    );
}

const formatDate = (dateString) => {
    const options = { day: 'numeric', month: 'long', year: 'numeric' };
    return new Date(dateString).toLocaleDateString(undefined, options);
}

export default Profile;
