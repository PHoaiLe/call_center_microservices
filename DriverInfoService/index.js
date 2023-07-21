const express = require('express');
  
const app = express();
const PORT = 8086;
  
app.listen(PORT, (error) =>{
    if(!error)
        console.log("Server is Successfully Running," 
                   +"and App is listening on port "+ PORT)
    else 
        console.log("Error occurred, server can't start", error);
    }
);

app.get("/", function(req, res, next)
{
    res.json("Hello, this is Driver-Info Service")
})

module.exports = app
