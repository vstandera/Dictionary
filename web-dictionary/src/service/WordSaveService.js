import axios from 'axios'

const SENTENCE_API_URL = 'http://localhost:8080'

class WordSaveService {

    saveWord(jsonWord, word) {
        console.log('paramkey :'+ word +'wordCategory: ' +JSON.stringify(jsonWord));
        axios.post(`${SENTENCE_API_URL}/words/`+ word, jsonWord)
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
    }
}

export default new WordSaveService()