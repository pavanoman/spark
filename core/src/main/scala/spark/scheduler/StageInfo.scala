package spark.scheduler

import cluster.TaskInfo
import collection._
import spark.util.Distribution

case class StageInfo(
    val stage: Stage,
    val taskInfos: mutable.Buffer[TaskInfo] = mutable.Buffer[TaskInfo](),
    val shuffleBytesWritten : mutable.Buffer[Long] = mutable.Buffer[Long](),
    val shuffleBytesRead : mutable.Buffer[Long] = mutable.Buffer[Long]()
) {

  def name = stage.rdd.name + "(" + stage.origin + ")"

  def getTaskRuntimeDistribution = {
    new Distribution(taskInfos.map{_.duration.toDouble})
  }
}
