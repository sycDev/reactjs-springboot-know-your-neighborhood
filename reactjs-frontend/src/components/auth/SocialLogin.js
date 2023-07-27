import './SocialLogin.css';

function SocialLogin(props) {
    return (
        <div className='social-login'>
            <a className="social-login-link" href={props.href}>
                <div className="social-icon-wrapper">
                    <img className="social-icon" src={props.imgSrc} alt={props.imgAlt} />
                </div>
                <span className="social-login-text">Continue with Google</span>
            </a>
        </div>
    );
}

export default SocialLogin;
