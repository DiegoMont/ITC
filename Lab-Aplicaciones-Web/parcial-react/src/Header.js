function Header() {
    return (
        <header>
            <a href="#."><img src="img/aribnb-logo.png" className='airbnb-logo' /></a>
            <nav>
                <h1>Become a Host</h1>
                <img src="img/world.svg" />
                <div className="user">
                    <img src="img/menu.svg" className="menu" />
                    <img src="https://picsum.photos/200" className="user-pic" />
                </div>
            </nav>
        </header>
    )
}

export default Header;
