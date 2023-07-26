import React from 'react';
import TabTitle from '../common/TabTitle';
import './About.css';

function About() {
    return (
        <>
            <TabTitle title="KYN | About Us" />
            <section className="banner-section">
                <div className="banner-overlay">
                    <h1>About Us</h1>
                </div>
            </section>

            <section className="introduction-section">
                <h2>Our Mission</h2>
                <p>
                    Know-Your-Neighborhood is dedicated to providing convenience and accessibility to our customers.
                    We strive to be the go-to destination for finding nearby grocery stores in various neighborhoods,
                    making your shopping experience more easy and enjoyable.
                </p>
                <p>
                    We offers a diverse selection of fresh and high-quality kitchen and household essentials, 
                    with a focus on innovative store concepts and efficient digital order fulfillment. We prioritize 
                    supporting local communities by offering our services in various localities.
                </p>
            </section>

            <section className="why-choose-us-section">
                <h2>Why Choose Us?</h2>
                <div className="card">
                    <p>
                        We offer up-to-date information about grocery stores in your area.
                    </p>
                </div>
                <div className="card">
                    <p>
                        Our website is easy to use, ensuring a smooth shopping experience.
                    </p>
                </div>
                <div className="card">
                    <p>
                        We value your feedback and continuously improve our services.
                    </p>
                </div>
            </section>

            <section className="how-it-works-section">
                <h2>How It Works</h2>
                <div className="steps-listing">
                    <ol>
                        <li>
                            <span className="number_divider">1</span>
                            Enter your location or allow the website to access your current location.
                        </li>
                        <li>
                            <span className="number_divider">2</span>
                            Explore nearby grocery stores on the interactive map or browse the list view.
                        </li>
                        <li>
                            <span className="number_divider">3</span>
                            Get detailed store information, including address, contact number, and opening hours.
                        </li>
                    </ol>
                </div>
            </section>
        </>
    );
}

export default About;
