import React from 'react';
import { Layout } from 'antd';
import { Content } from 'antd/es/layout/layout';
import AppRouter from '../app/AppRouter';
import Header from '../common/Nav';
import Footer from '../common/Footer';
import './App.css';

function App() {
	return (
		<Layout>
			<Header />
			<Content>
				<AppRouter />
			</Content>
			<Footer />
		</Layout>
	);
}

export default App;
