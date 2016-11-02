"use strict";

const _ = require("lodash");

function isKaprekar(number) {
  const nos = _.split("" + Math.pow(number, 2), '');
  return _.chain(_.range(1, nos.length))
          .map(i => _.chunk(nos, i))
          .map(list => [_.head(list).join(""), _.flatten(_.tail(list)).join("")])
          .map(pair => _.map(pair, n => Number(n)))
          .filter(pair => _.indexOf(pair, 0) === -1)
          .map(pair => _.sum(pair))
          .filter(n => n === number)
          .value().length > 0;
}

function kaprekarNosInRange(firstIndex, lastIndex) {
  return _.filter(_.range(firstIndex, lastIndex), isKaprekar);
}

console.log(kaprekarNosInRange(2, 100));
console.log(kaprekarNosInRange(101, 9000));
