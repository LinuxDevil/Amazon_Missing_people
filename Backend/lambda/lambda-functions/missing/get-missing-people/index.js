const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
const MissingPerson = require('/opt/nodejs/models/MissingPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    
    let resultLimit = 50;
    let resultsToSkip = event.page ? (event.page - 1) * resultLimit : 0;
    
    return MissingPerson.find().sort("-id").limit(resultLimit).skip(resultsToSkip);
};
