const mongoose = require('mongoose');
const connectionCheck = require('/opt/nodejs/checkConnection.js')(process.env.DB_CONN);
let MissingPerson = require('/opt/nodejs/models/MissingPerson.js');
let FoundPerson = require('/opt/nodejs/models/FoundPerson.js');

exports.handler = async (event,context) => {
    context.callbackWaitsForEmptyEventLoop = false;
    await connectionCheck;
    
    try {
            let personAlreadyFound = await FoundPerson.findOne(
                    {"name":event.body.name,"national_number":event.body.national_number}
                ).exec();
            if(personAlreadyFound) {
                return {
                    "status":200,
                    body:JSON.stringify({
                        "id":personAlreadyFound.id,
                        "message":"Person Already Found!"
                    })
                };
            }
            
            const newMissingPerson = new MissingPerson(event.body);
            await newMissingPerson.save();
            return {
                "status":200,
                body:JSON.stringify({
                    "message":"New Missing Person Added!",
                    "id":event.body.id
                })
            };
    }
    catch(err) {
        return {
            "status":400,
            body:JSON.stringify({
                "message":err.message
            })
        };
    }
};
