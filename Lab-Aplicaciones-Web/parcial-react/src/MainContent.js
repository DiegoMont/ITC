import Card from './Card';

function MainContent() {
    const numCards = 12;

    return (
        <section className="cards">
            <h1>Plan a trip with help from local Hosts around the world</h1>
            <div className="cards-container">
                {Array(numCards).fill().map((element, index) => {
                    const id = index + 1;
                    const cardInfo = {
                        img: `https://picsum.photos/240/340?r=${id}`
                    };
                    return <Card key={id} card={cardInfo}/>;
                })}
            </div>
        </section>
    );
}

export default MainContent;
