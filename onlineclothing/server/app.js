var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var bodyParser = require('body-parser');
var cors = require('cors');

var middleware = require('./middleware');

var app = express();


// view engine setup
app.set('views', path.join(__dirname, 'views'));

app.use(logger('dev'));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// parse application/json
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
  extended: true
}));
app.use(cors());





var accountApiRouter = require('./routes/api/account');
app.use('/api/account', accountApiRouter);

app.all('/api/*', middleware.checkToken);

var productApiRouter = require('./routes/api/product');
app.use('/api/product', productApiRouter);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

 
});

module.exports = app;