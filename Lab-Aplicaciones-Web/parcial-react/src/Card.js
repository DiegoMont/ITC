function Card({card}) {
    return (
        <div className="card">
            <img src={card.img} className="card-image" />
            <img src="img/heart.svg" className="like" />
            <img src="img/star.svg" className="star" />
            <span className="rating">4.98</span>
            <span className="reviews">(130)</span>
            <span className="country">· United States</span>
            <p className="place">Plan The Perfect New York Vacation</p>
            <p className="price">From $102 MXN / <span>person</span></p>
        </div>
    );
}

export default Card;
