
 // Copyright (c) 2015, The Regents of the University of California (Regents).
 // All Rights Reserved. See LICENSE for license details.
 //------------------------------------------------------------------------------
 
package chipyard.config
 

import org.chipsalliance.cde.config.Config
import org.chipsalliance.cde.config.Parameters
import freechips.rocketchip.rocket._
import freechips.rocketchip.subsystem.RocketTilesKey
import freechips.rocketchip.tile.{BuildCore, FPUParams, XLen}
import freechips.rocketchip.tilelink.TLEdgeOut
import casino.{CasinoCore, CasinoCoreParams, CasinoKey, GShareParameters}

class DefaultCasinoConfig extends Config((site, here, up) => {
  // Top-Level
  case BuildCore => (p: Parameters, e: TLEdgeOut) => new CasinoCore()(p, e)
  case XLen => 64

  // Rocket/Core Parameters
  case RocketTilesKey =>
    up(RocketTilesKey, site).map { r =>
      r.copy(core = r.core.copy(
        fWidth = 2,
        useCompressed = false,
        nPerfCounters = 4,
        nPerfEvents = 31,
        fpu = Some(FPUParams(sfmaLatency = 3, dfmaLatency = 3))
      ))
    }

  // CASINO-specific uarch Parameters
  case CasinoKey =>
    CasinoCoreParams(
      issueWidth = 3,
      numRobEntries = 48,
      numIssueSlotEntries = 20,
      numPhysRegisters = 110,
      numLsuEntries = 16,
      maxBrCount = 8,
      enableBranchPredictor = true,
      gshare = Some(GShareParameters(enabled = true, history_length = 11))
    )
})

class WithNPerfCounters(n: Int) extends Config((site, here, up) => {
  case RocketTilesKey =>
    up(RocketTilesKey, site).map { r =>
      r.copy(core = r.core.copy(nPerfCounters = n))
    }
})

// Small CASINO!
class WithSmallCasinos extends Config((site, here, up) => {
    case RocketTilesKey =>
    up(RocketTilesKey, site).map { r =>
      r.copy(core = r.core.copy(
        fWidth = 1,
        nPerfCounters = 1
      ))
    }
  case CasinoKey =>
    up(CasinoKey, site).copy(



      issueWidth = 1,
      numRobEntries = 24,
      numIssueSlotEntries = 10,
      numLsuEntries = 4,
      numPhysRegisters = 100,
      maxBrCount = 4,
      
      gshare = Some(GShareParameters(enabled = true, history_length = 11))
    )
})


// try to match the Cortex-A9
class WithMediumCasinos extends Config((site, here, up) => {
  case RocketTilesKey =>
    up(RocketTilesKey, site).map { r =>
      r.copy(core = r.core.copy(fWidth = 2))
    }
  case CasinoKey =>
    up(CasinoKey, site).copy(
      issueWidth = 3,
      numRobEntries = 48,
      numIssueSlotEntries = 20,
      numLsuEntries = 16,
      numPhysRegisters = 110,
      gshare = Some(GShareParameters(enabled = true, history_length = 11))
    )
})


//// try to match the Cortex-A15
class WithMegaCasinos extends Config((site, here, up) => {
     case RocketTilesKey =>
    up(RocketTilesKey, site).map { r =>
      r.copy(core = r.core.copy(fWidth = 4))
    }
  case CasinoKey =>
    up(CasinoKey, site).copy(
      issueWidth = 4,
      numRobEntries = 128,
      numIssueSlotEntries = 28,
      numLsuEntries = 32,
      numPhysRegisters = 128,
            gshare = Some(GShareParameters(enabled = true, history_length = 11))
    )
})