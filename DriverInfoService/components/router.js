const express = require("express")
const router = express();

const accountRouter = require("./account/router")
const authRouter = require("./auth/router");


router.use("/account", accountRouter);
router.use("/auth", authRouter);
router.use("/", function(req, res)
{
    res.json("Welcome!!!!")
})

module.exports = router