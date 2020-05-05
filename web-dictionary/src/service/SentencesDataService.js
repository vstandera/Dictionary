import axios from 'axios'

const SENTENCE_API_URL = 'http://localhost:8080'

class SentencesDataService {

    retrieveAllSentences() {
        //console.log('executed service')
        return axios.get(`${SENTENCE_API_URL}/sentences`,
        );
    }
}

export default new SentencesDataService()
