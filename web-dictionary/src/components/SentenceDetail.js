import React, { Component } from 'react';
import SentencesDetailDataService from "../service/SentencesDetailDataService";
import Card from 'react-bootstrap/Card'
import Button from 'react-bootstrap/Button';
import {Link} from "react-router-dom";

class SentenceDetail extends Component{

    constructor(props){
    super(props)
    this.state = {
        sentence: {sentence:{ text: null,
        message: null}
    }}
    this.refreshSentence = this.refreshSentence.bind(this)
}
    componentDidMount() {
        this.refreshSentence();
    }

    refreshSentence() {
        console.log('paramkey :'+ this.props.match.params.sentenceId);
        SentencesDetailDataService.retrieveDetailSentences(this.props.match.params.sentenceId)//HARDCODED
            .then(
                response => {
                    this.setState({ sentence: response.data })
                }
            )
    }

    render() {
        return (
            <div className="Align-center">

                <Card>
                    <Card.Header>Sentence</Card.Header>
                    <Card.Body>
                        <Card.Title>Generated sentene with id: {this.props.match.params.sentenceId} </Card.Title>
                        <Card.Text>
                            {this.state.sentence.sentence.text}.<br/>
                            Number of view: {this.state.sentence.sentence.numberOfView}
                        </Card.Text>
                        <Link to="/sentence"><Button variant="primary">Go back</Button></Link>
                    </Card.Body>
                </Card>
            </div>

        )
    }

}
export default SentenceDetail