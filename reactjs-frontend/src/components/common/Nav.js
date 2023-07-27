import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { Menu } from 'antd';
import './Nav.css';
import { Header } from 'antd/es/layout/layout';

const guestItems = [
    {
        key: "/",
        label: <Link to='/'>Home</Link>
    },
    {
        key: "/about",
        label: <Link to='/about'>About Us</Link>
    },
    {
        key: "/contact",
        label: <Link to='/contact'>Contact Us</Link>
    },
    {
        key: "/register",
        label: <Link to='/register'>Register</Link>
    },
    {
        key: "/login",
        label: <Link to='/login'>Login</Link>
    }
];

function Nav(props) {
    const [current, setCurrent] = useState('');

    const location = useLocation();
    useEffect(() => {
        setCurrent(location.pathname);
    }, [location]);

    const securedItems = [
        {
            key: "/",
            label: <Link to='/'>Home</Link>
        },
        {
            key: "/about",
            label: <Link to='/about'>About Us</Link>
        },
        {
            key: "/contact",
            label: <Link to='/contact'>Contact Us</Link>
        },
        {
            key: "/stores",
            label: <Link to='/stores'>Stores</Link>
        },
        {
            key: "/profile",
            label: props.currentUser ? (
                    <Link to='/profile'>
                        <b>{props.currentUser.name || props.currentUser.username}</b>
                    </Link>
                ) : null,
        },
        {
            key: "/logout",
            label: "Logout",
            style: { color: 'red' }
        },
    ];

    const handleMenuClick = ({ key }) => {
        if (key === "/logout") {
            props.onLogout();
        }
    };

    return (
        <Header 
            style={{
                position: 'sticky',
                top: 0,
                zIndex: 1,
                width: '100%',
                display: 'flex',
                alignItems: 'center'
            }}
        >
            <div className="logo">
                <span>Know Your Neighborhood</span>
            </div>
            <Menu
                theme="dark"
                mode="horizontal"
                defaultSelectedKeys={['/']}
                items={props.currentUser ? securedItems : guestItems}
                selectedKeys={[current]}
                onClick={handleMenuClick}
                style={{ minWidth: 0, flex: 'auto' }}
            />
        </Header>
    );
}

export default Nav;
