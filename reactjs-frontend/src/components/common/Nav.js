import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { Menu } from 'antd';
import './Nav.css';
import { Header } from 'antd/es/layout/layout';

const items = [
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

function Nav() {
    const [current, setCurrent] = useState('');

    const location = useLocation();
    useEffect(() => {
        setCurrent(location.pathname);
    }, [location]);

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
                items={items}
                selectedKeys={[current]}
                style={{ minWidth: 0, flex: 'auto' }}
            />
        </Header>
    );
}

export default Nav;