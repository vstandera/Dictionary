import React, { Component } from 'react';
import Button from 'react-bootstrap/Button';
import GenerateSentenceService from "../service/GenerateSentenceService"

class GenerateSentence extends Component{
    constructor(props) {
        super(props)
        this.state = {
            sentence: null,
            message: null
        }
         this.generateSentence = this.generateSentence.bind(this)
    }

    generateSentence() {
        GenerateSentenceService.generateSentences().then(response => {
            this.setState({ sentence: response.data })
        })

    }
    showSentence(){
        if (this.state.sentence!=null){
            return JSON.stringify(this.state.sentence, null, 2);
        }
        return "Not generated yet."
    }
    render() {
        console.log('generate')
        return (
            <div>
            <div className="Align-center">
            <Button variant="outline-primary" onClick={this.generateSentence}>
                Generate sentence
            </Button>
            </div>
                <div>
                    <pre>
                        {this.showSentence()}
                    </pre>
                </div>
            </div>
        )
    }
}

export default GenerateSentence