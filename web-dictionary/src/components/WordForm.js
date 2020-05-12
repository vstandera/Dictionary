import React, { Component } from 'react';
import Form from 'react-bootstrap/Form';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import WordSaveService from '../service/WordSaveService'



class WordForm extends Component {

    constructor(props){
        super(props)
        this.state = {
            word: '',
            wordCategory: ''
            }
        this.formWord = React.createRef();
        this.wordCategory = React.createRef();
        this.wordParam = React.createRef();
        this.setWordCategory = this.setWordCategory.bind(this);
        this.submitWord = this.submitWord.bind(this);
    }

    submitWord(event) {
        console.log("toto je ref:" + this.wordCategory.current.value);
        event.preventDefault();
        const jsonWord = {
            word: this.wordParam.current.value,
            wordCategory: this.wordCategory.current.value };
        const wordC=this.wordParam.current.value;
        WordSaveService.saveWord(jsonWord, wordC);
         this.formWord.current.reset();

    }
    // redundnat prepare for validation.
    setWordCategory(event){
        const wordCaC= event.target.value;
        this.setState({ wordCategory: wordCaC});
    }


    render() {
        return (
            <div>
            <div className="Align-center">
                <Form ref={this.formWord}>
                    <Form.Row>
                        <Form.Group as={Col} controlId="word">
                            <Form.Label>Word</Form.Label>
                            <Form.Control ref={this.wordParam} />
                        </Form.Group>
                        <Form.Group as={Col} controlId="wordCategory">
                            <Form.Label>Word category</Form.Label>
                            <Form.Control as="select" onChange={this.setWordCategory} ref={this.wordCategory}>
                                <option value="NOUN">NOUN</option>
                                <option value="VERB">VERB</option>
                                <option value="ADJECTIVE">ADJECTIVE</option>
                            </Form.Control>
                        </Form.Group>
                    </Form.Row>

                    <Button variant="primary" type="submit" onClick={this.submitWord}>
                        Submit
                    </Button>
                </Form>
            </div>
            </div>
        )
    }
}
export default WordForm