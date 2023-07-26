import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Home from '../pages/Home'
import About from '../pages/About';
import Contact from '../pages/Contact';
import Terms from '../pages/Terms';
import Register from '../auth/Register';
import Login from '../auth/Login';
import NotFound from '../pages/NotFound';
import RedirectOAuth from '../auth/RedirectOAuth';

function AppRouter() {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/about" element={<About />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/terms" element={<Terms />} />
            <Route path="/register" element={<Register />} />
            <Route path="/login" element={<Login />} />
            <Route path='*' element={<NotFound />} />
            <Route path='/oauth2/redirect' element={<RedirectOAuth />} />
        </Routes>
    );
}

export default AppRouter;
