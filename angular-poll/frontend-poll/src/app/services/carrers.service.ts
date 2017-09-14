import { Injectable } from '@angular/core';
import { Http } from '@angular/http'

import { CARRERS } from '../models/courses.mock'

@Injectable()
export default class CarrersService {

    constructor() {
    }

    getCarrers(){
      return Promise.resolve(CARRERS);
    }
}
