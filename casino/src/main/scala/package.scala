//******************************************************************************
// Copyright (c) 2015, The Regents of the University of California (Regents).
// All Rights Reserved. See LICENSE for license details.
//------------------------------------------------------------------------------

package object casino extends
   casino.constants.CASINODebugConstants with
   casino.constants.BrPredConstants with
   casino.constants.ScalarOpConstants with
   casino.constants.ExcCauseConstants with
   casino.constants.RISCVConstants
{
   val START_ADDR = 0x100
}
