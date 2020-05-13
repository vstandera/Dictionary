import axios from 'axios'

const SENTENCE_API_URL = 'http://localhost:8080'

class SentencesYodaDetailDataService {

    retrieveYodaDetailSentences(key) {
        console.log('paramkey :'+ key);
        return axios.get(`${SENTENCE_API_URL}/sentences/`+key+`/yodaTalk`,
        );
    }
}

export default new SentencesYodaDetailDataService()