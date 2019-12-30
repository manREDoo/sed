package manred.topo.server.framework.loadable.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有要刷新的静态表，所有Config开头的表请实现Ireload接口。以便于在线刷新配置，如有刷新不了的请考虑实现方式
 * @author yu.c
 *
 */
public enum ReloadType {

	ConfigCompensate,
	ConfigGrkidTaskBranch,
	ConfigTask,
	ConfigTaskPrice,
	ConfigTaskPriceRank,
	ConfigDegreeReward,
	ConfigSitDrop,
	ConfigGrowthTaskSup,
	ConfigGrowthTaskSon,
	
	ConfigRole,				//家将与布阵
	ConfigRoleAttr,
	ConfigGeneralInherit,
	ConfigRankAdmire,
	ConfigMajorRoleSkill,
	ConfigMajorRoleSkillMental,
	ConfigMonsterFighter,
	ConfigMonsterArmy,
				
	ConfigPassiveBuff,		//技能
	ConfigBuff,
	ConfigRobbersPosition,
	ConfigSkillCost,
	ConfigLevel,
	ConfigTurn,
	ConfigLifeCycle,
	ConfigMeetReward,
	ConfigRecharge,
	ConfigMilitary,
	
	ConfigRoleBasePotency,
	ConfigBGImage,
	ConfigRoleOnlyEquipUpgrade,
	
	ConfigDrawCost,			//抽奖
	ConfigDrawPool,
	ConfigDrawRule,
	
	ConfigArmy,
	
	ConfigForceHegemony, 
	ConfigForceLevel,
	ConfigForceSupply,
	ConfigForceMissionReward,//势力副本奖励
	ConfigForceWar,	
	ConfigForceWarDefend,	
	ConfigForceMissionOpen,//势力副本开启消耗
	ConfigForceWarReward,
	ConfigForceGoodsLimit,
	
	ConfigActivity,   //活动
	ConfigActivityCycle,   //活动
	ConfigActivityReward,
	ConfigCheckCode,//激活码
	ConfigConsumeTurn,
	ConfigNoviceReach,
	ConfigSit,
	
	
	ConfigMine,   
	ConfigMineTool,   
	ConfigMineProb,   
	ConfigMineAct,		//双倍挖矿配置 -byzk
	ConfigMineReflash,   
	
	ConfigEquipEnhance,
	ConfigEquipRefine,
	ConfigEquipXilian,
	ConfigEquipXilianTunshi,
	ConfigEquipUpgrade,
	ConfigEquipStarLevel,
	ConfigGoods,
	ConfigRegisterReward,
	ConfigGridCost,
	ConfigEquipGrid,
	ConfigGridGemstone,
	ConfigGridSpirit,
	ConfigTaskMarket,
	ConfigSDConsume,
	ConfigSDFormula,
	ConfigSpecialRing,
	ConfigOrange,
	ConfigLegend,
	ConfigWindExp,
	ConfigWindConsume,
	ConfigWindAttr,
	ConfigDrawGroupRewards,
	ConfigDrawChooseGroup,
	ConfigDrawInfo,
	ConfigArtifactConsume,
	ConfigArtifactExp,
	ConfigArtifactAttr,
	ConfigArtifactActivate,
	ConfigEquipZhuzai,
	ConfigGridZhuzai,
	ConfigWaferUpgrade,
	
	ConfigGeneralGrid,
	ConfigGeneralLevel,
	ConfigGeneralSpar,
	ConfigGeneralStar,
	ConfigGeneralTraining,
	ConfigGeneralWakeup,
	ConfigGeneralMarrow,
	ConfigGeneralMarrowExp,
	ConfigGeneralRestoreGoods,

	ConfigMission,
	ConfigMissionReward,
	ConfigMissionGroupReward,
	ConfigMissionGroupEnergyReward,
	ConfigDropOut,
	
	ConfigQualifyNPC,
	ConfigQualifyDisplay, //ConfigQualifyNPC,
	ConfigQualifyReward,
	ConfigQualifyDailyScoreReward,
	ConfigQualifyBuyNumCost,
	
	ConfigShop,
	ConfigShopMission,
	ConfigTrader,
	ConfigVein,
	ConfigVeinNeedle,
	ConfigVip,
	ConfigVipDesc,
	ConfigTrugaDesc,
	ConfigWuXing,
	ConfigWuXingLabel,
	ConfigRoleSkillLv,
	
	ConfigxhSkillRoleAction,
	
	ConfigSparMonster,
	ConfigLevelLimit,
	ConfigDayDiscount,
	ConfigLVRestrict,
	
	ConfigMainWuHun,
	configMainWuShi,
	
	configHermit,
	
	ConfigTitle,
	ConfigLevelReward,
	ConfigSceneLvReward,
	
	ConfigTreasure,
	ConfigXunxiaGiftCode,
	ConfigRoleName,
	
	ConfigNormalLuckilyDraw,
	ConfigNoviceActivity,
	
	ConfigArenaDegree,
	ConfigArenaQuality,
	ConfigArenaReward,
	ConfigArenaDegreeMonster,
	
	ConfigSimulateGel,
	
	ConfigServerInfo,
	ConfigWords,
	
	ConfigLimitMissionReward,
	
	ConfigLiveTrain, ConfigDescText,
	
	ConfigPersonalDrawReward,
	
	ConfigChallengeReward,
	ConfigChallengeDegree, 
	
	ConfigEquipBoxDrawCost, ConfigEquipBoxDrawPool,
	
	ConfigSurvival,
	ConfigSurvivalArmy,
	ConfigSurvivalCost,
	ConfigGridRefine,
	
	ConfigHorse,
	
	ConfigLimitBoxRankDrawCost,
	ConfigLimitBoxRankDrawPool,
	ConfigLimitBoxRankDrawReward,
	
	ConfigStoneCompound,
	
	ConfigFate,
	ConfigGoldCat,
	
	ConfigLimitedDrawCost,
	ConfigLimitedDrawPool,
	
	ConfigKingcraftLevelReward,
	ConfigKingcraftFightReward,
	ConfigKingcraftRankReward,
	
	ConfigForceBuilding,
	ConfigForceJob,
	ConfigForceSkill,
	ConfigForceBossReward,
	ConfigRobbersAppear,
	ConfigHegemonyReward,
	ConfigKuafuHegemonyReward,
	
	ConfigFactionBattleBuf,
	ConfigFactionBattleReward,
	
	ConfigFaMaCollect,
	ConfigFaMaUpgrade,
	
	ConfigHeraldryAttr,
	ConfigHeraldryBackpack,
	ConfigHeraldryShop,
	
	
	ConfigNightBattle,


	ConfigSoilder,
	ConfigGeneral,
	ConfigGeneralAttrCard,

	ConfigFightExpr,
	ConfigSkill,
	ConfigEmbattle,
	ConfigCity,
	ConfigCityPkTimes,
	ConfigVIP,
	ConfigVIPEffect,

	ConfigShakeAward,
	ConfigConvoyBusiness,
	ConfigTeahouse,
	ConfigSearchTower,
	ConfigReinforce,
	ConfigHuoYueTask,
	ConfigExchangeType,
	ConfigCuiLianDissolve,

	ConfigDailyTaskReward,
	ConfigDailyTask,

	ConfigUpLevelEquip,
	ConfigAttRange,
	ConfigPower,
	ConfigPowerNpc,
	ConfigPowerTour,
	ConfigGoodsAwards,

	ConfigTalent,
	ConfigReportCode,
	ConfigState,

	ConfigAwards,
	ConfigPageBoy,
	ConfigMartialBooks,
	ConfigLegionBadge,
	ConfigLegionRole,
	ConfigLegionSkill,
	ConfigLegionTask,
	ConfigPowerDrop,
	ConfigNpc,
	ConfigExchangeShop,
	ConfigGoldBody,
    ConfigClimb,
	ConfigTest,
	ConfigMoneyTree,
	ConfigRingAttrProb,
	ConfigRingAttribute,
	ConfigDailyTaskFlushRule,

	ConfigAcupoint,
	ConfigAcupointExp,
	ConfigMail,
	ConfigMasterApprentice,
    ConfigPlant, ConfigLegionFarm,
	ConfigPrestige,
	GeneralResourceExpr,
    ConfigMenu,
	ConfigGuideTask,
	ConfigJiangHuStory,
	ConfigActionLog,
	ConfigBossNpc,
	ConfigRewardTask,
    ConfigMaincityNpc,
    ConfigRandomAttNpc,
	ConfigFilterPlayer,
    ConfigNoviceActionLog,
	ConfigLittleTips,
	ConfigOpQueue,
	ConfigBeatNpcDrop,
	ConfigPet,
	ConfigPetLevel,
	ConfigPetFeed,
	ConfigPetHouse,
	ConfigCheque,
	ConfigBuild,
	ConfigGoodsBuy,
	ConfigExamination,
	ConfigScene,
	ConfigLegionSceneBuff,
	ConfigWulinCraftCell,
	ConfigPowerGroupHelper,
	ConfigAuctionGoods,
    ConfigRewardTaskFlushRule,
	ConfigAgainProvide,
    ConfigLuckMoneyGenerator,
	ConfigSearchDeerPowerClue,
	ConfigSearchDeerTask,
	ConfigSceneAuctionType,
    ;

	private static List<String> allReloadTypes = new ArrayList<String>();
	static {
		allReloadTypes.clear();
		for (ReloadType tpe : values()) {
			allReloadTypes.add(tpe.name());
		}
	}
	/**
	 * 返回全部的要刷新的类型
	 * @return
	 */
	public static List<String> getAllReloadTypes() {
		return allReloadTypes;
	}


	public static ReloadType getByName(String reloadTypeName){
		for (ReloadType ele : values()) {
			if(ele.name().equalsIgnoreCase(reloadTypeName))
				return ele;
		}
		return null;
	}

}
