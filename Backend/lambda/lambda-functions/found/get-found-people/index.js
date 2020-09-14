const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
let FoundPerson = require('/opt/nodejs/models/FoundPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    return FoundPerson.find().limit(10);
};
