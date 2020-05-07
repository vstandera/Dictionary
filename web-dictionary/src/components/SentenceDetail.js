import React, { Component } from 'react';
import SentencesDetailDataService from "../service/SentencesDetailDataService";


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
                <div>
                    <h3>Sentence Id: {this.props.match.params.sentenceId}</h3>
                    <h4>{this.state.sentence.sentence.text}.</h4>
                    <div>Number of view: {this.state.sentence.sentence.numberOfView}</div>
                </div>
            </div>

        )
    }

}
export default SentenceDetail