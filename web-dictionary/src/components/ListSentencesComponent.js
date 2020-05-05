import React, { Component } from 'react'
import SentencesDataService from '../service/SentencesDataService';

// const INSTRUCTOR = 'in28minutes'

class ListSentencesComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            sentences: [],
            message: null
        }
        this.refreshSentence = this.refreshSentence.bind(this)
    }

    componentDidMount() {
        this.refreshSentence();
    }

    refreshSentence() {
        SentencesDataService.retrieveAllSentences()//HARDCODED
            .then(
                response => {
                    this.setState({ sentences: response.data })
                }
            )
    }


    render() {
        console.log('render')
        return (
            <div className="container">
                <h3>All Sentences</h3>
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Noun</th>
                                <th>Verb</th>
                                <th>Adjectiv</th>
                                <th>Sentence usage</th>
                                <th>Generated time</th>
                                <th>Number of view</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.sentences.map(
                                    sentence =>
                                        <tr key={sentence.id}>
                                            <td>{sentence.id}</td>
                                            <td>{sentence.words[0].word}</td>
                                            <td>{sentence.words[1].word}</td>
                                            <td>{sentence.words[2].word}</td>
                                            <td>{sentence.sentenceUsageCount}</td>
                                            <td>{sentence.localDateTime}</td>
                                            <td>{sentence.numberOfView}</td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default ListSentencesComponent
