//******************************************************************************
// Copyright (c) 2015, The Regents of the University of California (Regents).
// All Rights Reserved. See LICENSE for license details.
//------------------------------------------------------------------------------

package casino

import Chisel._
import uncore.tilelink._
import uncore.coherence._
import uncore.agents._
import uncore.devices._
import uncore.converters._
import coreplex._
import rocketchip._
import tile._
import rocket._
import casino._
import config.{Parameters, Config}

// scalastyle:off

class CASINOConfig extends Config(new DefaultCasinoConfig ++ new WithNBigCores(1) ++ new WithoutTLMonitors ++ new BaseConfig)
class SmallCasinoConfig extends Config(new WithSmallCasinos ++ new DefaultCasinoConfig ++ new WithNBigCores(1) ++ new WithoutTLMonitors ++ new BaseConfig)
class MediumCasinoConfig extends Config(new WithMediumCasinos ++ new DefaultCasinoConfig ++ new WithNBigCores(1) ++ new WithoutTLMonitors ++ new BaseConfig)
class MegaCasinoConfig extends Config(new WithMegaCasinos ++ new DefaultCasinoConfig ++ new WithNBigCores(1) ++ new WithoutTLMonitors ++ new BaseConfig)

// scalastyle:on
