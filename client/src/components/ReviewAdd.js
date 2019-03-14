import React, { Component } from 'react';
import { connect } from 'react-redux';

import { reviewAdd } from '../actions/reviewActions';

class ReviewAdd extends Component {

    constructor() {
        super();
        this.state = {
            text: ''
        }
    }

    static getDerivedStateFromProps(nextProps, prevState) {
        const restaurantId = parseInt(/\/restaurants\/([0-9]*)\//.exec(nextProps.url)[1], 10);
        return {...prevState, restaurantId};
    }

    textChange(e) {
        this.setState({...this.state, text: e.target.value});
    }

    render() {
        return <div>Add a review
            <input type="text" name="text" value={this.state.text} onChange={(e) => this.textChange(e)}/>
            <button onClick={() => this.props.save(this.state)}>Save</button>
        </div>
    }
}

const mapStateToProps = (state) => {
    return {
        url: state.router.pathname
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        save: (review) => dispatch(reviewAdd(review))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(ReviewAdd);