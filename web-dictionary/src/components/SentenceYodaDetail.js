import React, { Component } from 'react';
import SentencesYodaDetailDataService from "../service/SentencesYodaDetailDataService";
import Card from 'react-bootstrap/Card'
import Button from 'react-bootstrap/Button';
import {Link} from "react-router-dom";

class SentenceYodaDetail extends Component{

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
        SentencesYodaDetailDataService.retrieveYodaDetailSentences(this.props.match.params.sentenceId)//HARDCODED
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
                    <Card.Header className="backgroundGreen"><i className="material-icons md-24">child_care</i>     Sentence Yoda </Card.Header>
                    <Card.Body>
                        <Card.Title>{this.state.sentence.sentence.text}.</Card.Title>
                        <Card.Text>
                            Generated yoda sentene with id: {this.props.match.params.sentenceId}
                        </Card.Text>
                        <Link to="/sentence"><Button variant="primary">Go back</Button></Link>
                    </Card.Body>
                </Card>
            </div>

        )
    }

}
export default SentenceYodaDetail