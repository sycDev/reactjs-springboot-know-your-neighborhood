import React from 'react';
import { Carousel } from 'antd';
import TabTitle from '../common/TabTitle';
import StoreImg1 from '../../assets/images/welcome1.jpg';
import StoreImg2 from '../../assets/images/welcome2.jpg';
import StoreImg3 from '../../assets/images/welcome3.jpg';
import StoreImg4 from '../../assets/images/welcome4.jpg';
import './Home.css';

function Home() {
    const imgStyle = {
        width: '100%',
        height: '100vh'
    };

    const carouselData = [
        {
            id: 1,
            image: StoreImg1,
            text: 'Discover Fresh Delights in Your Neighborhood!',
            headline: '24/7 Convenience - Your Nearest Store Awaits!',
            description: `Embrace around-the-clock convenience! Discover our nearby 24X7 grocery store, 
                        making shopping hassle-free, day or night.`,
        },
        {
            id: 2,
            image: StoreImg2,
            text: 'Find Convenience at Your Fingertips - Locate Your Nearest Store!',
            headline: 'Savor Local Flavors in Your Neighborhood!',
            description: `Embark on a gastronomic journey with our neighborhood grocery stores, 
                        offering a delightful range of locally sourced and fresh products.`,
        },
        {
            id: 3,
            image: StoreImg3,
            text: 'Shop Locally and Sustainably - Locate Our Stores Today!',
            headline: 'Eco-Friendly Choices, Right Where You Live!',
            description: `Join our eco-conscious movement! Find our sustainable grocery stores, 
                        promoting environmentally friendly products and practices.`,
        },
        {
            id: 4,
            image: StoreImg4,
            text: 'Quality Groceries Just Around the Corner - Explore Nearby Stores!',
            headline: 'Discover Community-Centric Shopping Experiences!',
            description: `Beyond groceries, we foster community spirit! Explore our stores, 
                        bringing people together and supporting local initiatives.`,
        },
    ];

    return (
        <>
            <TabTitle title="KYN | Home" />
            <div className="carousel-container">
                <Carousel autoplay autoplaySpeed={10000}>
                    {carouselData.map((slide) => (
                        <div key={slide.id}>
                            <div className="slide-image">
                                <img style={imgStyle} src={slide.image} alt={`Slide ${slide.id}`} />
                                <div className="slide-overlay">
                                    <h2>{slide.headline}</h2>
                                    <p>{slide.description}</p>
                                </div>
                            </div>
                        </div>
                    ))}
                </Carousel>
            </div>
        </>
    );
}

export default Home;
