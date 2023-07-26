import React from 'react';
import { Helmet } from 'react-helmet';

const TabTitle = ({ title }) => (
    <Helmet>
        <title>{title}</title>
    </Helmet>
);

export default TabTitle;
