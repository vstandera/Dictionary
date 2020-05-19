import axios from 'axios'

// const SENTENCE_API_URL = 'http://localhost:8080'
const SENTENCE_API_URL = 'http://3.136.156.3:3000'

class GenerateSentenceService {

    generateSentences() {
        //console.log('executed service')

        return axios.post(`${SENTENCE_API_URL}/sentences/generate`,);
    }
}

export default new GenerateSentenceService()
