const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
let MissingPerson = require('/opt/nodejs/models/MissingPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    
    
    let resultLimit = 2;
    let resultsToSkip = 0;
    if(event.params) {
      resultsToSkip = event.params.path.page ? (event.params.path.page - 1) * resultLimit : 0;
    }
      
    
    // base query
    let query = [
      {
        $search: {
          "compound": {
            "should": [],
              "minimumShouldMatch": 1
          }
        },
      },
      { $skip  : resultsToSkip  },
      { $limit : resultLimit }
      
    ];
    
    // adds to query based on availability
    if(event.body.name) {
      query[0].$search.compound.should.push({
                "text": {
                  "query": event.body.name,
                  "path": "name",
                  "fuzzy": {
                   "maxEdits": 2,
                  }
                }
              });
    }
    
    if(event.body.mother_name) {
      query[0].$search.compound.should.push({
                "text": {
                  "query": event.body.mother_name,
                  "path": "mother_name",
                  "fuzzy": {
                   "maxEdits": 2,
                  }
                }
              });
    }
    
    if(event.body.national_number) {
      query[0].$search.compound.should.push({
                "range": {
                  "gte": event.body.national_number,
                  "lte": event.body.national_number,
                  "path": "national_number",
                  "score": { "boost": { "value": 5 } }
                }
              });
    }
    
    if(event.body.gender) {
      query[0].$search.compound.should.push({
                "text": {
                  "query": event.body.gender,
                  "path": "gender",
                  "score": { "boost": { "value": 2 } }
                }
              });
    }
    
    const result = await MissingPerson.aggregate(query)

    if(result.length == 0) return "No Result Found.";
    
    return result;
};
