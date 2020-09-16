const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
const FoundPerson = require('/opt/nodejs/models/FoundPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    
    let resultLimit = 50;
    let resultsToSkip = event.page ? (event.page - 1) * resultLimit : 0;
    
    return FoundPerson.find().sort("-id").limit(resultLimit).skip(resultsToSkip);
};
