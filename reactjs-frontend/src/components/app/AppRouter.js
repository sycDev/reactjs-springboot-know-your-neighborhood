import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Home from '../pages/Home'
import About from '../pages/About';
import Contact from '../pages/Contact';
import Terms from '../pages/Terms';
import NotFound from '../pages/NotFound';
import Register from '../auth/Register';
import Login from '../auth/Login';
import RedirectOAuth from '../auth/RedirectOAuth';
import PrivateRoute from './PrivateRoute';
import Stores from '../store/Stores';
import Profile from '../user/Profile';

function AppRouter({ onLogin, isAuthenticated, currentUser }) {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/about" element={<About />} />
            <Route path="/contact" element={<Contact />} />
            <Route path="/terms" element={<Terms />} />
            <Route path="/register" element={<Register />} />
            <Route 
                path="/login" 
                element={<Login onLogin={onLogin} />} 
            />
            <Route path='/oauth2/redirect' element={<RedirectOAuth />} />
            <Route
                path="/stores"
                element={
                    <PrivateRoute authenticated={isAuthenticated}>
                        <Stores />
                    </PrivateRoute>
                }
            />
            <Route
                path="/profile"
                element={
                    <PrivateRoute authenticated={isAuthenticated}>
                        <Profile currentUser={currentUser} />
                    </PrivateRoute>
                }
            />
            <Route path='*' element={<NotFound />} />
        </Routes>
    );
}

export default AppRouter;
