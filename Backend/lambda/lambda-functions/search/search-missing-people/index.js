const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
let MissingPerson = require('/opt/nodejs/models/MissingPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;

    const result = await MissingPerson.aggregate([
      {
        $search: {
          "compound": {
            "should": [
              {
                "text": {
                  "query": event.name,
                  "path": "name",
                  "fuzzy": {
                   "maxEdits": 2,
                  }
                }
              },
              {
                "text": {
                  "query": event.mother_name,
                  "path": "mother_name",
                  "fuzzy": {
                   "maxEdits": 2,
                  }
                }
              },
              {
                "range": {
                  "gte": event.national_number,
                  "lte": event.national_number,
                  "path": "national_number",
                  "score": { "boost": { "value": 5 } }
                }
              },
              {
                "text": {
                  "query": event.gender,
                  "path": "gender",
                  "score": { "boost": { "value": 2 } }
                }
              }
              ],
              "minimumShouldMatch": 1
            }
          }
        }
    ])

    if(result.length == 0) return "No Result Found.";
    return result;
};
