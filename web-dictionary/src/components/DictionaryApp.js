import React, { Component } from 'react';
import ListSentencesComponent from './ListSentencesComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import LoginComponent from './LoginComponent';
import LogoutComponent from './LogoutComponent';
import MenuComponent from './MenuComponent';
import GenerateSentence from './GenerateSentence';
import AuthenticatedRoute from './AuthenticatedRoute';
import SentenceDetail from "./SentenceDetail";
import SentenceYodaDetail from "./SentenceYodaDetail";
import WordForm from "./WordForm";

class DictionaryApp extends Component {


    render() {
        return (
            <>
                <Router>
                    <>
                        <MenuComponent />
                        <Switch>
                            <Route path="/" exact component={LoginComponent} />
                            <Route path="/login" exact component={LoginComponent} />
                            <AuthenticatedRoute path="/logout" exact component={LogoutComponent} />
                            <AuthenticatedRoute path="/sentence" exact component={ListSentencesComponent} />
                            <AuthenticatedRoute path="/generate" exact component={GenerateSentence} />
                            <AuthenticatedRoute path="/senDetail/:sentenceId" exact component={SentenceDetail} />
                            <AuthenticatedRoute path="/senYodaDetail/:sentenceId" exact component={SentenceYodaDetail} />
                            <AuthenticatedRoute path="/wordForm" exact component={WordForm} />
                        </Switch>
                    </>
                </Router>
            </>
        )
    }
}

export default DictionaryApp