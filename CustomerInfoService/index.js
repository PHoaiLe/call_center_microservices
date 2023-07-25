const express = require('express');
  
const app = express();
const PORT = 8085;
  
app.listen(PORT, (error) =>{
    if(!error)
        console.log("Server is Successfully Running," 
                   +"and App is listening on port "+ PORT)
    else 
        console.log("Error occurred, server can't start", error);
    }
);

app.get("/", function(req, res)
{
    res.json("Hello this is Customer-Info Home")
})

app.get("/customer_info", function(req, res)
{
    res.json("Hello, this is Customer-Info Service")
})

module.exports = app
