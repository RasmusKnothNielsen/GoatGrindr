/* Built upon: LikeCarousel (c) 2019 Simone P.M. github.com/simonepm - Licensed MIT */
class MainPage {

    constructor() {

        this.board = document.querySelector('body');

        this.username = $("#userGoatID").text();

        this.user = /*[[${user}]]*/ "";
        this.candidates = /*[[${candidates}]]*/ "";

        // add first two cards
        this.push();
        this.push();

        // handle gestures
        this.handle()

    }

    push() {

        if(this.candidate) {
            this.currentCandidate = this.candidate;
        }

        // get candidate goat
        this.candidate = this.candidates.pop();

        // create the "card" div
        let card = document.createElement("div");
        card.classList.add("card");

        // create the "cardFront" div
        let cardFront = document.createElement("div");
        cardFront.classList.add("card-front");

        // create the "card-image" div
        let cardImage = document.createElement("div");
        cardImage.classList.add("card-image");

        // create the image for the "card-image" div
        let image = document.createElement("img");
        image.src = "https://placegoat.com/410/410"; // TODO - get specific image url from DB?
        image.style.width = "100%";
        image.style.height = "100%";

        // create the "card-footer" div
        let cardFooter = document.createElement("div");
        cardFooter.classList.add("card-footer");

        // create the h1 holding the name and age
        let nameAgeLabel = document.createElement("h1");
        nameAgeLabel.classList.add("name-age-label");
        let goatName = this.candidate.name;
        let goatAge = this.getAge(this.candidate.dob);
        nameAgeLabel.textContent = goatName + ", " + goatAge;

        // create the h2 holding the short description
        let shortDescription = document.createElement(("h2"));
        shortDescription.classList.add("short-description");
        shortDescription.textContent = this.candidate.shortDescription;

        // create the "cardBack" div
        let cardBack = document.createElement("div");
        cardBack.classList.add("card-back");

        // create the h1 holding the back-side headline
        let backHeadline = document.createElement(("h1"));
        backHeadline.classList.add("back-headline");
        backHeadline.textContent = goatName + "'s profile";

        let dividerLine = document.createElement(("hr"));
        dividerLine.classList.add("card-back-line");

        // create the p holding the long description
        let longDescription = document.createElement(("p"));
        longDescription.classList.add("long-description");
        longDescription.textContent = this.candidate.longDescription;

        // set the structure of the "card" div
        cardImage.append(image);
        cardFooter.append(nameAgeLabel);
        cardFooter.append(shortDescription);
        cardFront.append(cardImage);
        cardFront.append(cardFooter);
        cardBack.append(backHeadline);
        cardBack.append(dividerLine);
        cardBack.append(longDescription);
        card.append(cardFront);
        card.append(cardBack);

        if (this.board.firstChild) {
            this.board.insertBefore(card, this.board.firstChild)
        } else {
            this.board.append(card)
        }

    }

    handle() {

        // list all cards
        this.cards = this.board.querySelectorAll('.card');

        // get top card
        this.topCard = this.cards[this.cards.length-1];

        // get next card
        this.nextCard = this.cards[this.cards.length-2];

        // get buttons
        this.dislikeButton = document.querySelector('.left-action');
        this.likeButton = document.querySelector('.right-action');

        // if at least one card is present
        if (this.cards.length > 0) {

            // set default top card position and scale
            this.topCard.style.transform = 'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(1)';

            // destroy previous Hammer instances, if present
            if (this.hammer) this.hammer.destroy();
            if (this.dislikeHammer) this.dislikeHammer.destroy();
            if (this.likeHammer) this.likeHammer.destroy();

            // listen for tap and pan gestures on top card
            this.hammer = new Hammer(this.topCard);
            this.hammer.add(new Hammer.Tap());
            this.hammer.add(new Hammer.Pan({ position: Hammer.position_ALL, threshold: 0 }));

            // listen for tap gesures on dislike/like buttons
            this.dislikeHammer = new Hammer(this.dislikeButton);
            this.dislikeHammer.add(new Hammer.Tap());
            this.likeHammer = new Hammer(this.likeButton);
            this.likeHammer.add(new Hammer.Tap());

            // pass events data to custom callbacks
            this.hammer.on('tap', () => { this.onTap() });
            this.hammer.on('pan', (e) => { this.onPan(e) });
            this.dislikeHammer.on('tap', () => {this.onActionDislike()});
            this.likeHammer.on('tap', () => {this.onActionLike()});

        }
    }

    onTap() {

        // TODO - Swap contents of the card with profile-info and a play sound button?
        console.log("topCard tapped");

        // change the transition property
        this.topCard.style.transition = 'transform 500ms ease-out';

        // rotate
        if(!this.isFlipped) {
            this.topCard.style.transform = 'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(180deg) scale(1)';
            this.isFlipped = true;

            // TODO - play mating sound here
            // How do we handle sounds?


        } else {
            this.topCard.style.transform = 'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(1)';
            this.isFlipped = false;
        }

    }

    onPan(e) {

        if (!this.isPanning) {

            this.isPanning = true;

            // remove transition properties
            this.topCard.style.transition = null;
            if (this.nextCard) this.nextCard.style.transition = null;

            // get top card coordinates in pixels
            let style = window.getComputedStyle(this.topCard);
            let mx = style.transform.match(/^matrix\((.+)\)$/);
            this.startPosX = mx ? parseFloat(mx[1].split(', ')[4]) : -215; //TODO - normally last value was 0, how to avoid hardcoded values?
            this.startPosY = mx ? parseFloat(mx[1].split(', ')[5]) : -265;

            console.log("startPosX: " + this.startPosX);
            console.log("startPosY: " + this.startPosY);

            // get top card bounds
            let bounds = this.topCard.getBoundingClientRect();

            // get finger position on top card, top (1) or bottom (-1)
            this.isDraggingFrom = (e.center.y - bounds.top) > this.topCard.clientHeight / 2 ? -1 : 1

        }

        // calculate new coordinates
        let posX = e.deltaX + this.startPosX;
        let posY = e.deltaY + this.startPosY;

        // get ratio between swiped pixels and the axes
        let propX = e.deltaX / this.board.clientWidth;
        //let propY = e.deltaY / this.board.clientHeight;

        // get swipe direction, left (-1) or right (1)
        let dirX = e.deltaX < 0 ? -1 : 1;

        // calculate rotation, between 0 and +/- 45 deg
        let deg = this.isDraggingFrom * dirX * Math.abs(propX) * 45;

        // calculate scale ratio, between 95 and 100 %
        let scale = (95 + (5 * Math.abs(propX))) / 100;

        //check if card has been flipped
        let flip = (this.isFlipped) ? 180 : 0;

        // move top card
        this.topCard.style.transform = 'translateX(' + posX + 'px) translateY(' + posY + 'px) rotate(' + deg + 'deg) rotateY(' + flip + 'deg) scale(1)';

        // scale next card
        if (this.nextCard) this.nextCard.style.transform = 'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(' + scale + ')';

        if (e.isFinal) {

            this.isPanning = false;

            let successful = false;

            // set back transition properties
            this.topCard.style.transition = 'transform 200ms ease-out';
            if (this.nextCard) this.nextCard.style.transition = 'transform 100ms linear';

            // check threshold
            if (propX > 0.25 && e.direction === Hammer.DIRECTION_RIGHT) {

                this.liked();
                successful = true;
                // get right border position
                posX = this.board.clientWidth

            } else if (propX < -0.25 && e.direction === Hammer.DIRECTION_LEFT) {

                this.disliked();
                successful = true;
                // get left border position
                posX = - (this.board.clientWidth + this.topCard.clientWidth)

            }

            if (successful) {

                // throw card in the chosen direction
                this.topCard.style.transform = 'translateX(' + posX + 'px) translateY(' + posY + 'px) rotate(' + deg + 'deg)';

                // wait transition end
                setTimeout(() => {
                    // remove swiped card
                    this.board.removeChild(this.topCard);
                    // add new card
                    this.push();
                    // handle gestures on new top card
                    this.handle()
                }, 200)

            } else {

                // reset cards position
                this.topCard.style.transform = 'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(' + flip + 'deg) scale(1)';
                if (this.nextCard) this.nextCard.style.transform = 'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(0.95)'

            }
        }
    }

    onActionDislike() {

        this.disliked();

        // change the transition property
        this.topCard.style.transition = 'transform 400ms ease-in';
        this.nextCard.style.transition = 'transform 400ms ease-in';

        // throw card in the chosen direction
        let posX = -(this.board.clientWidth + this.topCard.clientWidth);
        this.topCard.style.transform = 'translateX(' + posX + 'px) translateY(-150px) rotate(-15deg)';
        if (this.nextCard) this.nextCard.style.transform = 'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(1)';

        // wait transition end
        setTimeout(() => {
            // remove swiped card
            this.board.removeChild(this.topCard);
            // add new card
            this.push();
            // handle gestures on new top card
            this.handle()
        }, 200);

    }

    onActionLike() {

        this.liked();

        // change the transition property
        this.topCard.style.transition = 'transform 400ms ease-in';
        this.nextCard.style.transition = 'transform 400ms ease-in';

        // throw card in the chosen direction
        let posX = this.board.clientWidth;
        this.topCard.style.transform = 'translateX(' + posX + 'px) translateY(-150px) rotate(15deg)';
        if (this.nextCard) this.nextCard.style.transform = 'translateX(-50%) translateY(-50%) rotate(0deg) rotateY(0deg) scale(1)';

        // wait transition end
        setTimeout(() => {
            // remove swiped card
            this.board.removeChild(this.topCard);
            // add new card
            this.push();
            // handle gestures on new top card
            this.handle()
        }, 200);

    }

    disliked() {
        this.isFlipped = false;
        let token = $("meta[name='_csrf']").attr("content");
        let data = {
            "goatDisliker": this.user,
            "goatDisliked": this.currentCandidate
        };
        $.ajax({
            url: "/api/dislike",
            headers: {"X-CSRF-TOKEN": token},
            dataType: "text",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function() {
                console.log("Disliking worked")
            },
            error: function() {
                console.log("Disliking didn't work...")
            }
        });
    }

    liked() {
        this.isFlipped = false;
        let token = $("meta[name='_csrf']").attr("content");
        let data = {
            "goatLiker": this.user,
            "goatLiked": this.currentCandidate
        };
        $.ajax({
            url: "/api/like",
            headers: {"X-CSRF-TOKEN": token},
            dataType: "text",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function() {
                console.log("Liking worked")
            },
            error: function() {
                console.log("Liking didn't work...")
            }
        });

        //Check for match
        $.ajax({
            url: "/api/checkmatch",
            headers: {"X-CSRF-TOKEN": token},
            dataType: "text",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function() {
                console.log("Checking for matches worked")
                //TODO - Is anything returned we should handle?
            },
            error: function() {
                console.log("Checking for matches didn't work...")
            }
        });

    }

    getAge(DOB) {
        let today = new Date();
        let birthDate = new Date(DOB);
        let age = today.getFullYear() - birthDate.getFullYear();
        let m = today.getMonth() - birthDate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
            age = age - 1;
        }

        return age;
    }

    settings() {

    }
}

$(document).ready(function(){
    new MainPage();
});