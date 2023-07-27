import './SocialLogin.css';

function SocialLogin({ href, imgSrc, imgAlt }) {
    return (
        <div className='social-login'>
            <a className="social-login-link" href={href}>
                <div className="social-icon-wrapper">
                    <img className="social-icon" src={imgSrc} alt={imgAlt} />
                </div>
                <span className="social-login-text">Continue with Google</span>
            </a>
        </div>
    );
}

export default SocialLogin;
