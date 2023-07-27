import React from 'react';
import { Link } from 'react-router-dom';
import './Footer.css';

function Footer() {
    return (
        <div className='footer'>
            <small>
                © 2023 Know-Your-Neighborhood
                <br/>
                <Link to='/terms'><b>Terms and Conditions</b></Link>
            </small>
        </div>
    );
}

export default Footer;
